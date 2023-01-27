const urlPrefix = "http://localhost:8080";

export async function callServer(url, data) {
  const response = await fetch(urlPrefix + url, {
    method: "POST",
    body: data ? JSON.stringify(data) : "",
    headers: {
      "Content-Type": "application/json",
    },
  });
  return await response.json();
}
