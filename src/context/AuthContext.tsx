import axios, { type AxiosResponse } from "axios";
import { createContext, useContext, useState } from "react";
import { useServerUrlContext } from "../App";

export type AuthContextType = {
  authorization: string | null;
  saveAuthorization: (token: string) => void;
  removeAuthorization: () => void;
  login : ({
    username,
    password,
  }: {
    username: string;
    password: string;
  }) => Promise<void>;
};

const AuthContext = createContext<AuthContextType>({
  authorization: null,
  saveAuthorization: () => {},
  removeAuthorization: () => {},
  login : async ({}) => {}
});

export const AuthContextProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const URL = useServerUrlContext();

  const token = localStorage.getItem("Authorization");

  const [authorization, setAuthorization] = useState<string | null>(token);

  const saveAuthorization = (token: string) => {
    localStorage.setItem("Authorization", token);
    setAuthorization(token);
  };

  const removeAuthorization = () => {
    localStorage.removeItem("Authorization");
    setAuthorization(null);
  };

  const login = async ({
    username,
    password,
  }: {
    username: string;
    password: string;
  }) => {

    const res = await axios.post<
      any,
      AxiosResponse<{ token: string }, any, any>
    >(`${URL}/api/v1/auth`, {
      username,
      password,
    });

    const { status, data } = res;

    if (status === 201) {
      saveAuthorization(data.token);
    }
  };

  return (
    <AuthContext.Provider
      value={{ authorization, saveAuthorization, removeAuthorization, login }}
    >
      {children}
    </AuthContext.Provider>
  );
};

const useAuthContext = (): AuthContextType => {
  return useContext(AuthContext);
};

export default useAuthContext;
