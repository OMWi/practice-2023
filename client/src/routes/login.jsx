import { useState } from "react";
import {
  Stack,
  Avatar,
  Button,
  TextField,
  Link,
  Box,
  Typography,
  Container,
  Paper,
} from "@mui/material";
import { AccountCircleOutlined } from "@mui/icons-material";
import { useNavigate, useOutletContext } from "react-router-dom";

import AuthService from "../services/auth";

export default function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const setAuth = useOutletContext()[1];

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
    if (!validateEmail().valid || !validatePassword().valid) {
      return;
    }
    AuthService.login(email, password)
      .then((response) => {
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
          setAuth(true);
          navigate(`/profile`);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <Container maxWidth="xs" sx={{ padding: 1 }}>
      <Paper square sx={{ padding: 2, marginTop: 6 }}>
        <Stack
          alignItems="center"
          sx={{
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <AccountCircleOutlined />
          </Avatar>
          <Typography component="h1" variant="h5">
            Login
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              size="small"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              autoFocus
              onChange={(event) => setEmail(event.target.value)}
              error={!validateEmail().valid}
              helperText={validateEmail().helperText}
            />
            <TextField
              margin="normal"
              size="small"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
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
              Login
            </Button>
            <Stack alignItems="flex-end">
              <Link href="/register" variant="body2">
                Don't have an account? Register
              </Link>
            </Stack>
          </Box>
        </Stack>
      </Paper>
    </Container>
  );
}
