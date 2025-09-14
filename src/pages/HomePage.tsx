import { Outlet } from "react-router-dom"



const HomePage = () => {
  return (
    <>
    <h1>Hello from home page</h1>
    <Outlet/>
    </>
  )
}

export default HomePage