import keycloak from "./keycloak";
import { ReactKeycloakProvider, useKeycloak } from "@react-keycloak/web";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Dashboard from "./pages/Dashboard";
import type { ReactNode } from "react";

function App() {
  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      onTokens={(tokens) => {
        console.log("Tokens:", tokens);
      }}
    >
      <AppRoutes />
    </ReactKeycloakProvider>
  );
}

/* ✅ Private Route (no FC) */
function PrivateRoute({ children }: { children: ReactNode }) {
  const { keycloak, initialized } = useKeycloak();

  if (!initialized) {
    return <div>Loading...</div>;
  }

  const isLoggedIn = keycloak?.authenticated ?? false;

  if (!isLoggedIn) {
    return <Navigate to="/" replace />;
  }

  return <>{children}</>;
}

function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />

        <Route
          path="/dashboard"
          element={
            <PrivateRoute>
              <Dashboard />
            </PrivateRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;