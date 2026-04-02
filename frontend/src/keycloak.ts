import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:8989/",
  realm: "springboot-test",
  clientId: "react-app",
});

export default keycloak;