import { useEffect, useState } from "react";
import { useKeycloak } from "@react-keycloak/web";
import { fetchCars } from "../services/api";

type Car = {
  name: string;
  color: string;
  price: number;
};

const Cars = () => {
  const { keycloak } = useKeycloak();

  const [cars, setCars] = useState<Car[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const getCars = async () => {
      try {
        if (!keycloak?.authenticated) return;

        // ✅ Important: refresh token properly
        await keycloak.updateToken(30);

        const data = await fetchCars(keycloak.token!);
        setCars(data);
      } catch (err: any) {
        console.error(err);
        setError("Failed to load cars");
      } finally {
        setLoading(false);
      }
    };

    getCars();
  }, [keycloak?.authenticated]);

  if (loading) return <div>Loading cars...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div style={{ marginTop: "20px" }}>
      {cars.map((car) => (
        <div
          key={car.name}
          style={{
            padding: "10px",
            marginBottom: "10px",
            border: "1px solid #ccc",
            borderRadius: "6px",
          }}
        >
          {car.name} - {car.color} | ₹{car.price}
        </div>
      ))}
    </div>
  );
};

export default Cars;