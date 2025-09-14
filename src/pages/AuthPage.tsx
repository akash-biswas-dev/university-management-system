import { Outlet } from "react-router-dom"

const AuthPage = () => {
  return (
    <>
      <h1>Hello from auth page</h1>
      <Outlet/>
    </>
  )
}

export default AuthPage