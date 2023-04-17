import {
  Button,
  Container,
  List,
  Paper,
  Stack,
  Typography,
} from "@mui/material";
import { useLoaderData, useNavigate, useOutletContext } from "react-router-dom";

import AuthService from "../services/auth";
import UserDataService from "../services/user-data";
import UserWordItem from "../components/UserWordItem";
import WordlistItem from "../components/WordlistItem";
import { useEffect, useState } from "react";

export async function loader() {
  const user = AuthService.getCurrentUser();
  if (user) {
    let userData = {};
    return Promise.all([
      UserDataService.get(user.userId),
      UserDataService.getUserWordLists(user.userId),
      UserDataService.getUserWords(user.userId),
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

export default function Profile() {
  const navigate = useNavigate();
  const userData = useLoaderData();

  const [auth, setAuth] = useOutletContext();

  useEffect(() => {
    if (!auth) {
      navigate("/login");
    }
  }, [auth]);

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Paper sx={{ padding: 2 }}>
        {userData && (
          <>
            <Stack alignItems="center">
              <Typography variant="h4">
                {userData.username} - {userData.email}
              </Typography>
            </Stack>
            <Stack alignItems="flex-end">
              <Typography variant="h6" sx={{ mb: 1 }}>
                {userData.points} points
              </Typography>
              <Button
                variant="contained"
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

            {userData.words.length > 0 && (
              <>
                <Stack alignItems="center" sx={{ mt: 3 }}>
                  <Typography variant="h5">My Words</Typography>
                </Stack>
                <List>
                  {userData.words.map((word) => (
                    <UserWordItem
                      key={word.wordId}
                      userWordData={{
                        wordId: word.wordId,
                        word: word.word,
                        isLearned: word.isLearned,
                        guessStreak: word.guessStreak,
                        category: word.category,
                      }}
                    />
                  ))}
                </List>
              </>
            )}

            {userData.wordLists.length > 0 && (
              <>
                <Stack alignItems="center" sx={{ mt: 4 }}>
                  <Typography variant="h5">Selected Word Lists</Typography>
                </Stack>
                <List>
                  {userData.wordLists.map((wordList) => (
                    <WordlistItem
                      key={wordList.id}
                      wordlistData={{
                        id: wordList.id,
                        name: wordList.name,
                        popularity: wordList.popularity,
                      }}
                    />
                  ))}
                </List>
              </>
            )}
          </>
        )}
      </Paper>
    </Container>
  );
}
