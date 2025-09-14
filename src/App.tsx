import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom"
import { AuthPage, HomePage, SignInPage, SignUpage } from "./pages"


let router = createBrowserRouter([
  {
    path: "/home",
    element: <HomePage/>
  },
  {
    path: "/auth",
    element: <AuthPage/>,
    children:[
      {
        index:true,
        element:<SignInPage/>
      },
      {
        path:"signup",
        element:<SignUpage/>
      }
    ]
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
