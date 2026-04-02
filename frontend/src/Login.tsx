import { useKeycloak } from "@react-keycloak/web";

const Login = () => {
  const { keycloak } = useKeycloak();

  const handleLogin = () => {
    keycloak.login();
  };

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      <h2>Welcome</h2>

      <button onClick={handleLogin}>
        Login with Keycloak
      </button>
    </div>
  );
};

export default Login;