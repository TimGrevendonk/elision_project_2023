const urlPrefix = process.env.NEXT_PUBLIC_JAVA_BASE_LINK;

export async function callServerPost(url, data) {
  const response = await fetch(urlPrefix + url, {
    method: "POST",
    mode: "cors",
    body: data ? JSON.stringify(data) : "",
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "http://development.runemannaerts.com",
      "Access-Control-Allow-Credentials": "true",
    },
  });

  return await response.json();
}

export async function callServerGet(url) {
  const response = await fetch(urlPrefix + url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });
  return await response.json();
}

export async function callServerPostNoJson(url, data) {
  let responseStatus = {};
  const userData = await fetch(urlPrefix + url, {
    method: "POST",
    body: data ? JSON.stringify(data) : "",
    headers: {
      "Content-Type": "application/json",
    },
  }).then(function (response) {
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
