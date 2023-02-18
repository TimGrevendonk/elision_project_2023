const urlPrefix = process.env.NEXT_PUBLIC_JAVA_BASE_LINK;

export async function callServerPost(url, data) {

  // code brent
  console.log("functie 1");
  console.log("[debug] url for backend", urlPrefix + url);
  console.log("[debug] url for backend", data);
  
  const response = await fetch(urlPrefix + url, {
    method: "POST",
    body: data ? JSON.stringify(data) : "",
    headers: {
      "Content-Type": "application/json",
    },
  });

  return await response.json();
}

export async function callServerGet(url) {

  // code brent
  console.log("functie 2");
  console.log("[debug] url for backend", urlPrefix + url);
  
  const response = await fetch(urlPrefix + url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });
  return await response.json();
}

export async function callServerPostNoJson(url, data) {

  // code brent
  console.log("functie 3");
  console.log("[debug] url for backend", urlPrefix + url);
  //console.log("[debug] url for backend", data);
  const obj = {"name": "jan5", "mail": "jan5@gmail.com", "password": "jan5"};
  const staticdata = JSON.stringify(obj);
  //console.log("static data", staticdata);
  
  const response = await fetch(urlPrefix + url, {
    method: "POST",
    body: data ? JSON.stringify(data) : "",
    //body: staticdata,
    headers: {
      "Content-Type": "application/json",
    },
  });
  return response;
}
