import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom"
import HomePage from "./pages/HomePage"
import AuthPage from "./pages/AuthPage"


let router = createBrowserRouter([
  {
    path: "/home",
    element: <HomePage/>
  },
  {
    path: "/auth",
    element: <AuthPage/>
  },
  {
    path:"/",
    element:<Navigate to="/home" replace/>
  }
])

function App() {

  return (
    <>
      <RouterProvider router={router} />
    </>
  )
}

export default App
