import { useKeycloak } from "@react-keycloak/web";

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
        Logout
      </button>

      <h3>User Info:</h3>
       <code style={{ whiteSpace: "pre-wrap", textAlign: "left" }}>
            {JSON.stringify(keycloak.tokenParsed, null, 2)}
      </code>
    </div>
  );
};

export default Dashboard;