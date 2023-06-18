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
  Paper,
} from "@mui/material";
import { AccountCircleOutlined } from "@mui/icons-material";
import AuthService from "../services/auth";
import { ValidationError, validateMinLength, validateRequired } from "../utils";

export default function Register() {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [userNameError, setUserNameError] = useState(
    ValidationError(false, ""),
  );
  const [emailError, setEmailError] = useState(ValidationError(false, ""));
  const [passwordError, setPasswordError] = useState(
    ValidationError(false, ""),
  );

  const setAuth = useOutletContext()[1];
  const navigate = useNavigate();

  const handleUserNameChange = (event) => {
    const newUserName = event.target.value;
    setUserName(newUserName);
    validateUserName(newUserName);
  };

  const validateUserName = (userName) => {
    if (!validateRequired(userName)) {
      setUserNameError(ValidationError(true, "User name required"));
      return false;
    }
    setUserNameError(ValidationError(false, ""));
    return true;
  };

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
    if (
      !validateUserName(userName) ||
      !validateEmail(email) ||
      !validatePassword(password)
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
      if (error.response.status === 409) {
        setEmailError(ValidationError(true, "Email already taken"));
      }
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <Paper square sx={{ marginTop: 8, padding: 2 }}>
        <Box
          sx={{
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
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 1 }}
          >
            <TextField
              size="small"
              margin="dense"
              required
              fullWidth
              id="firstName"
              label="User Name"
              name="userName"
              autoFocus
              error={userNameError.error}
              helperText={userNameError.helperText}
              onChange={(event) => {
                handleUserNameChange(event);
              }}
            />
            <TextField
              size="small"
              margin="dense"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              error={emailError.error}
              helperText={emailError.helperText}
              onChange={(event) => {
                handleEmailChange(event);
              }}
            />
            <TextField
              size="small"
              margin="dense"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="new-password"
              error={passwordError.error}
              helperText={passwordError.helperText}
              onChange={(event) => {
                handlePasswordChange(event);
              }}
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
              <Link href="/login" underline="always">
                <Typography variant="body2">
                  Already have an account? Login
                </Typography>
              </Link>
            </Stack>
          </Box>
        </Box>
      </Paper>
    </Container>
  );
}
