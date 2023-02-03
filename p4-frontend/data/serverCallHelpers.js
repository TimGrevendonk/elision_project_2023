const urlPrefix = "http://localhost:8080";

export async function callServerPost(url, data) {
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
  const response = await fetch(urlPrefix + url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });
  return await response.json();
}

export async function callLoginPost(mailInfo, passwordInfo) {
  const loginData = {
    mail: mailInfo,
    password: passwordInfo,
  };

  const JSONdata = JSON.stringify(loginData);

  const endpoint = process.env.NEXT_PUBLIC_JAVA_BASE_LINK + "/user/sign-in";

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSONdata,
  };
  const response = await fetch(endpoint, options);
  return response;
}

export async function callRegisterPost(nameInfo, mailInfo, passwordInfo) {
  const newUserData = {
    name: nameInfo,
    mail: mailInfo,
    password: passwordInfo,
  };

  const JSONdata = JSON.stringify(newUserData);

  const endpoint = process.env.NEXT_PUBLIC_JAVA_BASE_LINK + "/user/create";

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSONdata,
  };

  const response = await fetch(endpoint, options);
  return response;
}
