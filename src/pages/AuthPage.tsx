import { Outlet, useNavigate } from "react-router-dom"
import useAuthContext from "../context/AuthContext"

const AuthPage = () => {
  
  const {authorization} = useAuthContext();
  
  const navigation = useNavigate();

  if(authorization){
    navigation('/dashboard')
  }
  return (
    <>
      <Outlet/>
    </>
  )
}

export default AuthPage