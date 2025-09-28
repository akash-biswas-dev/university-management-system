import {
  createBrowserRouter,
  Navigate,
  RouterProvider,
} from "react-router-dom";
import { AuthPage, Dashboard, HomePage, SignInPage } from "./pages";
import { AuthContextProvider } from "./context/AuthContext";
import { createContext, useContext } from "react";

let router = createBrowserRouter([
  {
    path: "/home",
    element: <HomePage />,
  },
  {
    path: "/auth",
    element: <AuthPage />,
    children: [
      {
        index: true,
        element: <SignInPage />,
      }
    ],
  },
  {
    path: "/dashboard",
    element: <Dashboard />,
  },
  {
    path: "/",
    element: <Navigate to="/home" replace />,
  },
]);




function App() {

  const URL = import.meta.env.VITE_BASE_URL || "";

  console.log(URL);

  return (
    <ServerUrlContext.Provider value={URL}>
      <AuthContextProvider>
        <RouterProvider router={router} />
      </AuthContextProvider>
    </ServerUrlContext.Provider>
  );
}

export default App;


export const ServerUrlContext = createContext<string>("");


export const useServerUrlContext = () => useContext(ServerUrlContext);