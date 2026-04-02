import { useKeycloak } from "@react-keycloak/web";

const Home = () => {
  const { keycloak } = useKeycloak();

  const handleLogin = async () => {
    try {
      await keycloak.login({
        redirectUri: window.location.origin + "/dashboard",
      });
    } catch (err) {
      console.error("Login failed", err);
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      <h1>Home Page</h1>

      <button onClick={handleLogin}>
        Login with Keycloak
      </button>

    </div>
  );
};

export default Home;