import { Container, List, Paper, Stack, Typography } from "@mui/material";
import { useLoaderData, useNavigate } from "react-router-dom";

import AuthService from "../services/auth";
import UserDataService from "../services/user-data";
import UserWordItem from "../components/UserWordItem";
import WordlistItem from "../components/WordlistItem";

export async function loader() {
  const user = AuthService.getCurrentUser();
  let userData = {};
  if (user) {
    userData = (await UserDataService.get(user.userId)).data;
    let wordLists = (await UserDataService.getUserWordLists(user.userId)).data;
    userData.wordLists = wordLists;
    let words = (await UserDataService.getUserWords(user.userId)).data;
    userData.words = words;
    userData.email = user.email;
  }
  console.log(userData);
  return userData;
}

export default function Profile() {
  const navigate = useNavigate();
  const userData = useLoaderData();

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Paper sx={{ padding: 2 }}>
        <Typography variant="h4">
          {userData.username} - {userData.email}
        </Typography>

        <Typography variant="h6">{userData.points} points</Typography>

        {userData.words.length > 0 && (
          <>
            <Stack alignItems="center">
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
            <Stack alignItems="center">
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
      </Paper>
    </Container>
  );
}
