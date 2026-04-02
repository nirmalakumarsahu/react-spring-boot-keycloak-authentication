import { useKeycloak } from "@react-keycloak/web";
import Cars from "./Cars";

const Dashboard = () => {
  const { keycloak } = useKeycloak();

  return (
    <div>
      <h2>Dashboard</h2>

      <button
        onClick={() =>
          keycloak.logout({
            redirectUri: window.location.origin,
          })
        }
      >
        Logout ({keycloak?.tokenParsed?.name})
      </button>

      <h3>User Info:</h3>
       <code style={{ whiteSpace: "pre-wrap", textAlign: "left" }}>
            {JSON.stringify(keycloak.tokenParsed, null, 2)}
      </code>

      <Cars/>
    </div>
  );
};

export default Dashboard;