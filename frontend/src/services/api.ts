export const fetchCars = async (token: string) => {
  const response = await fetch("http://localhost:9898/api/v1/cars", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    throw new Error("Failed to fetch cars");
  }

  return response.json();
};