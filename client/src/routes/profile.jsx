import {
  Avatar,
  Button,
  Container,
  Divider,
  List,
  Paper,
  Stack,
  Tab,
  Tabs,
  TextField,
  Typography,
  useMediaQuery,
  useTheme,
} from "@mui/material";
import {
  useLoaderData,
  useLocation,
  useNavigate,
  useOutletContext,
} from "react-router-dom";

import AuthService from "../services/auth";
import UserDataService from "../services/user-data";
import PaymentService from "../services/payment";
import UserWordItem from "../components/UserWordItem";
import UserWordlistItem from "../components/UserWordlistItem";
import { useEffect, useState } from "react";
import {
  ValidationError,
  stringToColor,
  validateRequired,
  validateMinLength,
} from "../utils";

export async function loader() {
  const user = AuthService.getCurrentUser();
  if (user) {
    let userData = {};
    return Promise.all([
      UserDataService.getUserData(user.userId),
      UserDataService.listUserWordLists(user.userId),
      UserDataService.listUserWords(user.userId),
    ]).then((responses) => {
      userData = responses[0].data;
      userData.wordLists = responses[1].data;
      userData.words = responses[2].data;
      userData.email = user.email;
      return userData;
    });
  } else {
    return null;
  }
}

function ProfileUserData({ userData, navigate, setAuth }) {
  const handleCheckoutClick = async () => {
    const res = await PaymentService.getCheckoutUrl();
    window.location.href = res.data;
  };

  const [username, setUsername] = useState(userData.username);
  const [usernameError, setUsernameError] = useState(
    ValidationError(false, ""),
  );
  const [email, setEmail] = useState(userData.email);
  const [emailError, setEmailError] = useState(ValidationError(false, ""));
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordError, setPasswordError] = useState(
    ValidationError(false, ""),
  );
  const [confirmPasswordError, setConfirmPasswordError] = useState(
    ValidationError(false, ""),
  );

  const handleUsernameChange = (event) => {
    const newUsername = event.target.value;
    setUsername(newUsername);
    validateUsername(newUsername);
  };

  const validateUsername = (username) => {
    if (!validateRequired(username)) {
      setUsernameError(ValidationError(true, "Username required"));
      return false;
    }
    setUsernameError(ValidationError(false, ""));
    return true;
  };

  const handleUsernameSave = async () => {
    if (!validateUsername(username)) {
      return;
    }

    try {
      await UserDataService.updateUserData(
        AuthService.getCurrentUser().userId,
        username,
      );
      navigate(0);
    } catch (err) {
      console.log(err);
    }
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

  const handleEmailSave = async () => {
    if (!validateEmail(email)) {
      return;
    }

    try {
      const res = await AuthService.updateCredentials(email, null);
      if (res.data.token) {
        localStorage.setItem("user", JSON.stringify(res.data));
        setAuth(true);
      }
      navigate(0);
    } catch (error) {
      console.log("email update error:", error);
      if (error.response.status === 409) {
        setEmailError(ValidationError(true, "Email already taken"));
      }
    }
  };

  const handlePasswordChange = (event) => {
    const newPassword = event.target.value;
    setPassword(newPassword);
    validatePassword(newPassword);
  };

  const handleConfirmPasswordChange = (event) => {
    const newConfirmPassword = event.target.value;
    setConfirmPassword(newConfirmPassword);
    validateConfirmPassword(newConfirmPassword);
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

  const validateConfirmPassword = (confirmPassword) => {
    if (!validateRequired(password)) {
      setConfirmPasswordError(
        ValidationError(true, "Enter same password to confirm"),
      );
      return false;
    }
    if (password !== confirmPassword) {
      setConfirmPasswordError(ValidationError(true, "Passwords do not match"));
      return false;
    }
    setConfirmPasswordError(ValidationError(false, ""));
    return true;
  };

  const handlePasswordSave = async () => {
    if (
      !validatePassword(password) ||
      !validateConfirmPassword(confirmPassword)
    ) {
      return;
    }

    try {
      const res = await AuthService.updateCredentials(null, password);
      if (res.data.token) {
        localStorage.setItem("user", JSON.stringify(res.data));
        setAuth(true);
      }
      navigate(0);
    } catch (error) {
      console.log("change password error:", error);
    }
  };

  return (
    <Stack spacing={2}>
      <Stack
        alignItems="center"
        justifyContent="center"
        direction="row"
        spacing={1.5}
      >
        <Avatar
          sx={{
            bgcolor: stringToColor(userData.username),
          }}
        >
          {userData.username[0].toUpperCase()}
        </Avatar>
        <Typography variant="h4">{userData.username}</Typography>
      </Stack>

      <Divider />

      <Typography variant="h6" textAlign="center">
        {userData.exp} exp
      </Typography>
      <Stack spacing={1}>
        <Typography variant="subtitle2">User Name</Typography>
        <TextField
          size="small"
          value={username}
          onChange={handleUsernameChange}
          error={usernameError.error}
          helperText={usernameError.helperText}
        />
        {userData.username !== username && (
          <Stack direction="row" spacing={1}>
            <Button
              variant="contained"
              size="small"
              disableElevation
              onClick={() => handleUsernameSave()}
            >
              Save
            </Button>
            <Button
              variant="outlined"
              size="small"
              onClick={() => {
                setUsername(userData.username);
                setUsernameError(ValidationError(false, ""));
              }}
            >
              Cancel
            </Button>
          </Stack>
        )}
      </Stack>
      <Stack spacing={1}>
        <Typography variant="subtitle2">Email</Typography>
        <TextField
          size="small"
          value={email}
          onChange={handleEmailChange}
          error={emailError.error}
          helperText={emailError.helperText}
        />
        {userData.email !== email && (
          <Stack direction="row" spacing={1}>
            <Button
              variant="contained"
              size="small"
              disableElevation
              onClick={() => handleEmailSave()}
            >
              Save
            </Button>
            <Button
              variant="outlined"
              size="small"
              onClick={() => {
                setEmail(userData.email);
                setEmailError(ValidationError(false, ""));
              }}
            >
              Cancel
            </Button>
          </Stack>
        )}
      </Stack>
      <Stack spacing={1}>
        <Typography variant="subtitle2">Change Password</Typography>
        <TextField
          name="password1"
          size="small"
          label="New password"
          value={password}
          onChange={handlePasswordChange}
          error={passwordError.error}
          helperText={passwordError.helperText}
        />
        <TextField
          name="password2"
          size="small"
          label="Confirm new password"
          value={confirmPassword}
          onChange={handleConfirmPasswordChange}
          error={confirmPasswordError.error}
          helperText={confirmPasswordError.helperText}
        />
        {password !== "" && (
          <Stack direction="row" spacing={1}>
            <Button
              variant="contained"
              size="small"
              disableElevation
              onClick={() => handlePasswordSave()}
            >
              Save
            </Button>
            <Button
              variant="outlined"
              size="small"
              onClick={() => {
                setPassword("");
                setConfirmPassword("");
                setPasswordError(ValidationError(false, ""));
                setConfirmPasswordError(ValidationError(false, ""));
              }}
            >
              Cancel
            </Button>
          </Stack>
        )}
      </Stack>

      <Divider />

      {userData.isSubscriber && (
        <Typography variant="body1">
          Premium functions available until{" "}
          {userData.subscriptionExpirationDate}
        </Typography>
      )}

      <Stack direction="row" spacing={1}>
        {!userData.isSubscriber && (
          <Button
            variant="contained"
            disableElevation
            onClick={() => {
              handleCheckoutClick();
            }}
          >
            Buy Premium
          </Button>
        )}

        <Button
          variant="outlined"
          color="error"
          onClick={() => {
            AuthService.logout();
            setAuth(false);
            navigate("/");
          }}
        >
          Logout
        </Button>
      </Stack>
    </Stack>
  );
}

function ProfileWords({ userData, navigate }) {
  return (
    <Stack spacing={1}>
      <Stack alignItems="center" direction="row">
        <Stack sx={{ flexGrow: 1 }}>
          <Typography variant="h5">My Words</Typography>
        </Stack>
        <Button variant="contained" onClick={() => navigate("/learn")}>
          Learn
        </Button>
      </Stack>
      <Divider />
      <List>
        {userData.words.map((word) => (
          <UserWordItem key={word.wordId} userWordData={word} />
        ))}
      </List>
    </Stack>
  );
}

function ProfileWordLists({ userData, navigate }) {
  return (
    <Stack spacing={1}>
      <Stack alignItems="center" direction="row">
        <Stack sx={{ flexGrow: 1 }}>
          <Typography variant="h5">Saved Lists</Typography>
        </Stack>
        {userData.isSubscriber && (
          <Button
            variant="contained"
            onClick={() => navigate("/word-list-create")}
          >
            Create
          </Button>
        )}
      </Stack>

      <Divider />

      <List>
        {userData.wordLists.map((wordList) => (
          <UserWordlistItem key={wordList.wordListId} wordlistData={wordList} />
        ))}
      </List>
    </Stack>
  );
}

export default function Profile() {
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("sm"));

  const navigate = useNavigate();
  const userData = useLoaderData();

  const location = useLocation();
  const query = new URLSearchParams(location.search);

  const [auth, setAuth] = useOutletContext();
  const tabIndex = parseInt(query.get("tab") || 0, 10);

  useEffect(() => {
    if (!auth) {
      navigate("/login");
    }
  }, [auth]);

  return (
    <Container maxWidth="lg" sx={{ padding: 1, height: 1 }}>
      <Stack
        direction={isSmallScreen ? "column" : "row"}
        height={1}
        width={1}
        spacing={1}
      >
        <Tabs
          value={tabIndex}
          onChange={(event, value) => {
            navigate(`/profile${value == 0 ? "" : `?tab=${value}`}`);
            // setTabIndex(value);
          }}
          orientation={isSmallScreen ? "horizontal" : "vertical"}
          scrollButtons={false}
          TabIndicatorProps={{
            style: { display: "none" },
          }}
          textColor="inherit"
          sx={{
            // mx: "auto",
            my: 0,
            "& button": {
              backgroundColor: "selected.main",
            },
            "& button.Mui-selected": { backgroundColor: "primary.main" },
            "& button:hover": { backgroundColor: "secondary.main" },
          }}
        >
          <Tab label="User" />
          <Tab label="My Words" />
          <Tab label="My Lists" />
        </Tabs>
        <Paper sx={{ padding: 2, flexGrow: 1 }}>
          {tabIndex === 0 && userData && (
            <ProfileUserData
              userData={userData}
              navigate={navigate}
              setAuth={setAuth}
            />
          )}
          {tabIndex === 1 && userData && (
            <ProfileWords userData={userData} navigate={navigate} />
          )}
          {tabIndex === 2 && userData && (
            <ProfileWordLists userData={userData} navigate={navigate} />
          )}
        </Paper>
      </Stack>
    </Container>
  );
}
