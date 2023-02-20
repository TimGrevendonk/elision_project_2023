const urlPrefix = process.env.NEXT_PUBLIC_JAVA_BASE_LINK;

export async function callServerPost(url, data) {
  // code brent
  console.log(
    "[debug] callServerPost url and data for backend\n\n",
    urlPrefix + url,
    data
  );

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
  console.log("[debug] callServerGet url for backend\n\n", urlPrefix + url);

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
  console.log(
    "[debug] callServerPostNoJson url and data for backend\n\n",
    urlPrefix + url,
    data
  );
  console.log("[debug] stringy data", JSON.stringify(data));

  let responseStatus = {};
  const userData = await fetch(urlPrefix + url, {
    method: "POST",
    body: data ? JSON.stringify(data) : "",
    headers: {
      "Content-Type": "application/json",
    },
  }).then(function (response) {
    console.log("[debug] here", response);
    responseStatus = response;
    return response.json();
  });
  // .then(function (data) {
  //   return data;
  // });

  return { responseStatus, userData };
}

export async function callServerPostNoJsonconvert(url, data) {
  const response = await fetch(urlPrefix + url, {
    method: "POST",
    body: data ? JSON.stringify(data) : "",
    headers: {
      "Content-Type": "application/json",
    },
  });

  return response;
}
