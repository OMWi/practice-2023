import { useState } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";

export default function Root() {
  const [auth, setAuth] = useState(false);

  return (
    <>
      <Navbar auth={auth}></Navbar>
      <Outlet context={[auth, setAuth]}></Outlet>
    </>
  );
}
