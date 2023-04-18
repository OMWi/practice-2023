import { useEffect, useState } from "react";
import { useLoaderData, useOutletContext, useNavigate } from "react-router-dom";
import {
  Container,
  Divider,
  List,
  ListItem,
  Paper,
  Stack,
  Typography,
  Button,
} from "@mui/material";

import UserDataService from "../services/user-data";
import AuthService from "../services/auth";

export async function loader() {
  const user = AuthService.getCurrentUser();
  if (!user) return null;
  try {
    return (await UserDataService.getRandomUnlearnedUserWord(user.userId)).data;
  } catch (error) {
    return null;
  }
}

export default function LearnWord() {
  const word = useLoaderData();
  const navigate = useNavigate();
  const auth = useOutletContext()[0];

  const [visibleMeanings, setVisibleMeanings] = useState(false);

  useEffect(() => {
    if (!auth) {
      navigate("/login");
    }
  }, [auth]);

  const handleCorrectAnswer = async () => {
    await UserDataService.incUserWordGuessStreak(
      AuthService.getCurrentUser().userId,
      word.wordId,
    );
    setVisibleMeanings(false);
    navigate("/learn");
  };

  const handleWrongAnswer = async () => {
    await UserDataService.setZeroGuessStreak(
      AuthService.getCurrentUser().userId,
      word.wordId,
    );
    setVisibleMeanings(false);
    navigate("/learn");
  };

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      {auth && !word && (
        <Stack alignItems="center">
          <Typography variant="h4">
            Congratulations! All words learned
          </Typography>
        </Stack>
      )}

      {auth && word && (
        <Paper sx={{ padding: 2 }}>
          <Stack spacing={1}>
            <Stack direction="row" alignItems="flex-end">
              <Typography variant="h5" sx={{ display: "flex", flexGrow: 1 }}>
                {word.word}
              </Typography>
              <Stack direction="row" alignItems="center" spacing={1}>
                <Typography
                  textAlign="center"
                  variant="subtitle1"
                  fontStyle="italic"
                  sx={{ ml: 1 }}
                >
                  {word.category}
                </Typography>
              </Stack>
            </Stack>

            <Divider sx={{ my: 0.5 }} />

            <Stack>
              <Typography variant="subtitle2">
                {word.isLearned
                  ? `Word Learned`
                  : `Current guess streak: ${word.guessStreak}`}
              </Typography>
            </Stack>

            <Divider sx={{ my: 0.5 }} />

            {visibleMeanings ? (
              <>
                <List>
                  {word.meaningDtoList.length > 0 &&
                    word.meaningDtoList.map((meaningDto, index) => (
                      <ListItem disablePadding key={meaningDto.id}>
                        <Stack
                          direction="row"
                          alignItems="flex-start"
                          sx={{ width: 1 }}
                        >
                          <Typography variant="body1">
                            {index + 1}. [L{meaningDto.level}]
                          </Typography>
                          <Typography
                            variant="body1"
                            sx={{ mx: 1, overflow: "auto" }}
                          >
                            {meaningDto.text}
                          </Typography>
                        </Stack>
                      </ListItem>
                    ))}
                </List>
                <Stack direction="row" justifyContent="space-between">
                  <Button
                    color="error"
                    variant="contained"
                    onClick={() => handleWrongAnswer()}
                  >
                    Wrong
                  </Button>
                  <Button
                    variant="contained"
                    onClick={() => handleCorrectAnswer()}
                  >
                    Correct
                  </Button>
                </Stack>
              </>
            ) : (
              <Stack alignItems="center">
                <Button
                  variant="outlined"
                  onClick={() => setVisibleMeanings(true)}
                >
                  Show Meanings
                </Button>
              </Stack>
            )}
          </Stack>
        </Paper>
      )}
    </Container>
  );
}
