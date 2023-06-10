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
import { useLoaderData, useNavigate, useOutletContext } from "react-router-dom";

import AuthService from "../services/auth";
import UserDataService from "../services/user-data";
import UserWordItem from "../components/UserWordItem";
import WordlistItem from "../components/WordlistItem";
import { useEffect, useState } from "react";
import { stringToColor } from "../utils";

export async function loader() {
  const user = AuthService.getCurrentUser();
  if (user) {
    let userData = {};
    console.log("start fetcing data");
    return Promise.all([
      UserDataService.getUserData(user.userId),
      UserDataService.listUserWordLists(user.userId),
      UserDataService.listUserWords(user.userId),
    ]).then((responses) => {
      console.log("all data fetched");
      userData = responses[0].data;
      userData.wordLists = responses[1].data;
      userData.words = responses[2].data;
      userData.email = user.email;
      console.log("user data: ", userData);
      return userData;
    });
  } else {
    return null;
  }
}

export default function Profile() {
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("sm"));

  const navigate = useNavigate();
  const userData = useLoaderData();

  const [auth, setAuth] = useOutletContext();

  const [tabIndex, setTabIndex] = useState(0);

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
            setTabIndex(value);
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
            <Stack spacing={2}>
              <Stack
                alignItems="center"
                justifyContent="center"
                direction="row"
                spacing={1.5}
              >
                <Avatar
                  sx={{
                    bgcolor: stringToColor(
                      AuthService.getCurrentUser().username,
                    ),
                  }}
                >
                  {AuthService.getCurrentUser().username[0].toUpperCase()}
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
                  defaultValue={userData.username}
                  InputProps={{ readOnly: true }}
                />
              </Stack>
              <Stack spacing={1}>
                <Typography variant="subtitle2">Email</Typography>
                <TextField
                  size="small"
                  defaultValue={userData.email}
                  InputProps={{ readOnly: true }}
                />
              </Stack>
              <Stack spacing={1}>
                <Typography variant="subtitle2">Change Password</Typography>
                <TextField name="password1" size="small" label="New password" />
                <TextField
                  name="password2"
                  size="small"
                  label="Confirm new password"
                />
              </Stack>

              <Divider />

              <Stack direction="row" spacing={1}>
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
          )}
          {tabIndex === 1 && userData.words.length > 0 && (
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
          )}
          {tabIndex === 2 && userData.wordLists.length > 0 && (
            <Stack spacing={1}>
              <Stack alignItems="center" direction="row">
                <Stack sx={{ flexGrow: 1 }}>
                  <Typography variant="h5">Saved Lists</Typography>
                </Stack>
                <Button
                  variant="contained"
                  onClick={() => navigate("/word-list-create")}
                >
                  Create
                </Button>
              </Stack>

              <Divider />

              <List>
                {userData.wordLists.map((wordList) => (
                  <WordlistItem key={wordList.id} wordlistData={wordList} />
                ))}
              </List>
            </Stack>
          )}
        </Paper>
      </Stack>
    </Container>
  );
}
