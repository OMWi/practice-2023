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
  Chip,
  Link,
} from "@mui/material";
import VisibilityIcon from "@mui/icons-material/Visibility";
import CloseIcon from "@mui/icons-material/Close";
import CheckIcon from "@mui/icons-material/Check";

import UserDataService from "../services/user-data";
import AuthService from "../services/auth";

export async function loader() {
  const user = AuthService.getCurrentUser();
  if (!user) return null;
  try {
    return (await UserDataService.getQuestionWord(user.userId)).data;
  } catch (error) {
    return null;
  }
}

export default function LearnWord() {
  const userWord = useLoaderData();
  const navigate = useNavigate();
  const auth = useOutletContext()[0];

  const [visibleMeanings, setVisibleMeanings] = useState(false);

  useEffect(() => {
    if (!auth) {
      navigate("/login");
    }
  }, [auth]);

  const handleAnswer = async (isCorrect) => {
    await UserDataService.handleQuestionAnswer(
      userWord.userId,
      userWord.wordId,
      isCorrect,
    );
    setVisibleMeanings(false);
    navigate("/learn");
  };

  return (
    <Container maxWidth="md" sx={{ padding: 1 }}>
      {auth && !userWord && (
        <Stack alignItems="center">
          <Typography variant="h6">
            It seems like you've gone through all your practice for today.
          </Typography>
          <Typography variant="h6">
            Check back tomorrow to see if there are any new words to practice.
          </Typography>
          <Link href="/profile?tab=1" underline="alway">
            Go Back
          </Link>
        </Stack>
      )}

      {auth && userWord && (
        <Paper sx={{ padding: 2 }}>
          <Stack spacing={1}>
            <Stack direction="row">
              <Stack sx={{ flexGrow: 1 }}>
                <Typography textAlign="center" variant="h5">
                  {userWord.word}
                </Typography>
              </Stack>

              <Stack direction="row" alignItems="center" spacing={1}>
                <Typography
                  textAlign="center"
                  variant="subtitle1"
                  fontStyle="italic"
                  // sx={{ ml: 1 }}
                >
                  {userWord.category}
                </Typography>
              </Stack>
            </Stack>

            <Divider />

            {/* <Divider sx={{ my: 0.5 }} />

            <Stack>
              <Typography variant="subtitle2">
                {userWord.isLearned
                  ? `Word Learned`
                  : `Current guess streak: ${userWord.guessStreak}`}
              </Typography>
            </Stack>

            <Divider sx={{ my: 0.5 }} /> */}

            <List sx={visibleMeanings ? {} : { filter: "blur(20px)" }}>
              {userWord.meaningDtoList.length > 0 &&
                userWord.meaningDtoList.map((meaningDto) => (
                  <ListItem disablePadding key={meaningDto.id} sx={{ mb: 1 }}>
                    <Stack
                      direction="row"
                      alignItems="flex-start"
                      sx={{ width: 1 }}
                    >
                      <Chip
                        size="small"
                        label={meaningDto.difficulty}
                        variant="outlined"
                      />

                      <Typography
                        variant="body1"
                        sx={{ mx: 1, overflow: "auto" }}
                      >
                        {meaningDto.meaning}
                      </Typography>
                    </Stack>
                  </ListItem>
                ))}
            </List>

            {visibleMeanings ? (
              <>
                <Stack direction="row" justifyContent="space-between">
                  <Button
                    color="error"
                    variant="contained"
                    onClick={() => handleAnswer(false)}
                    startIcon={<CloseIcon />}
                  >
                    Wrong
                  </Button>
                  <Button
                    variant="contained"
                    onClick={() => handleAnswer(true)}
                    startIcon={<CheckIcon />}
                  >
                    Correct
                  </Button>
                </Stack>
              </>
            ) : (
              <Stack alignItems="center">
                <Button
                  variant="contained"
                  startIcon={<VisibilityIcon />}
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
