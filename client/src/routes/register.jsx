import { useState } from "react";
import { useOutletContext, useNavigate } from "react-router-dom";
import {
  Stack,
  Avatar,
  Button,
  TextField,
  Link,
  Box,
  Typography,
  Container,
} from "@mui/material";
import { AccountCircleOutlined } from "@mui/icons-material";
import AuthService from "../services/auth";

export default function Register() {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const setAuth = useOutletContext()[1];
  const navigate = useNavigate();

  const validateUserName = () => {
    if (userName === "") {
      return { valid: false, helperText: "User name required" };
    }
    return { valid: true, helperText: "" };
  };

  const validateEmail = () => {
    if (email === "") {
      return { valid: false, helperText: "Email required" };
    }
    return { valid: true, helperText: "" };
  };

  const validatePassword = () => {
    if (password === "") {
      return { valid: false, helperText: "Password required" };
    }
    if (password.length < 4) {
      return {
        valid: false,
        helperText: "Password should be atleast 4 characters long",
      };
    }
    return { valid: true, helperText: "" };
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (
      !validateEmail().valid ||
      !validatePassword().valid ||
      !validateUserName().valid
    ) {
      return;
    }
    try {
      await AuthService.register(userName, email, password);
      const loginResponse = (await AuthService.login(email, password)).data;
      if (loginResponse.token) {
        localStorage.setItem("user", JSON.stringify(loginResponse));
        setAuth(true);
        navigate("/profile");
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
          <AccountCircleOutlined />
        </Avatar>
        <Typography component="h1" variant="h5">
          Register
        </Typography>
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="firstName"
            label="User Name"
            name="userName"
            autoFocus
            onChange={(event) => setUserName(event.target.value)}
            error={!validateUserName().valid}
            helperText={validateUserName().helperText}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            onChange={(event) => setEmail(event.target.value)}
            error={!validateEmail().valid}
            helperText={validateEmail().helperText}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="new-password"
            onChange={(event) => setPassword(event.target.value)}
            error={!validatePassword().valid}
            helperText={validatePassword().helperText}
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Register
          </Button>

          <Stack alignItems="flex-end">
            <Link href="/login">
              <Typography variant="body2">
                Already have an account? Login
              </Typography>
            </Link>
          </Stack>
        </Box>
      </Box>
    </Container>
  );
}
