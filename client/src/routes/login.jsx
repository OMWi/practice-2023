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
  FormHelperText,
} from "@mui/material";
import { AccountCircleOutlined } from "@mui/icons-material";
import { useNavigate, useOutletContext } from "react-router-dom";

import { ValidationError, validateMinLength, validateRequired } from "../utils";
import AuthService from "../services/auth";

export default function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [emailError, setEmailError] = useState(ValidationError(false, ""));
  const [passwordError, setPasswordError] = useState(
    ValidationError(false, ""),
  );
  const [globalError, setGlobalError] = useState(ValidationError(false, ""));

  const setAuth = useOutletContext()[1];

  const handleEmailChange = (event) => {
    const newEmail = event.target.value;
    setEmail(newEmail);
    validateEmail(newEmail);
  };

  const validateEmail = (email) => {
    if (!validateRequired(email)) {
      setEmailError(ValidationError(true, "Email required"));
      return false;
    }
    setEmailError(ValidationError(false, ""));
    return true;
  };

  const handlePasswordChange = (event) => {
    const newPassword = event.target.value;
    setPassword(newPassword);
    validatePassword(newPassword);
  };

  const validatePassword = (password) => {
    if (!validateRequired(password)) {
      setPasswordError(ValidationError(true, "Password required"));
      return false;
    }
    if (!validateMinLength(password, 4)) {
      setPasswordError(
        ValidationError(true, "Password should be atleast 4 characters long"),
      );
      return false;
    }
    setPasswordError(ValidationError(false, ""));
    return true;
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!validateEmail(email) || !validatePassword(password)) {
      return;
    }

    try {
      const loginResponse = (await AuthService.login(email, password)).data;
      if (loginResponse.token) {
        localStorage.setItem("user", JSON.stringify(loginResponse));
        setAuth(true);
        navigate("/profile");
      }
    } catch (error) {
      console.log("login error", error);
      if (error.response.status === 401) {
        setGlobalError(ValidationError(true, "Wrong email or password"));
      }
    }
  };

  return (
    <Container maxWidth="xs" sx={{ padding: 1 }}>
      <Paper square sx={{ padding: 2, marginTop: 8 }}>
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
              margin="dense"
              size="small"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              autoFocus
              error={emailError.error}
              helperText={emailError.helperText}
              onChange={(event) => handleEmailChange(event)}
            />
            <TextField
              margin="dense"
              size="small"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              error={passwordError.error}
              helperText={passwordError.helperText}
              onChange={(event) => handlePasswordChange(event)}
            />
            <FormHelperText error={globalError.error}>
              {globalError.error && globalError.helperText}
            </FormHelperText>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Login
            </Button>
            <Stack alignItems="flex-end">
              <Link href="/register" variant="body2" underline="always">
                Don't have an account? Register
              </Link>
            </Stack>
          </Box>
        </Stack>
      </Paper>
    </Container>
  );
}
