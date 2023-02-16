import { atom, selector } from "recoil";

export const recoilproductsToBuy = atom({
  key: "productToBuy",
  default: {},
});

export const recoilLoggedIn = atom({
  key: "loggedIn",
  default: false,
});
