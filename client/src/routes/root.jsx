import { useState } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";
import { Box } from "@mui/material";

export default function Root() {
  const [auth, setAuth] = useState(localStorage.getItem("user") !== null);

  return (
    <Box
      sx={{
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        bgcolor: "background.main",
      }}
    >
      <Navbar auth={auth}></Navbar>
      <Outlet context={[auth, setAuth]} sx={{ flexGrow: 1 }} />
    </Box>
  );
}
