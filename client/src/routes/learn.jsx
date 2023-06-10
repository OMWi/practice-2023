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
    console.log("rework needed");
    // await UserDataService.incUserWordGuessStreak(
    //   AuthService.getCurrentUser().userId,
    //   word.wordId,
    // );
    setVisibleMeanings(false);
    navigate("/learn");
  };

  const handleWrongAnswer = async () => {
    console.log("rework needed");
    // await UserDataService.setZeroGuessStreak(
    //   AuthService.getCurrentUser().userId,
    //   word.wordId,
    // );
    setVisibleMeanings(false);
    navigate("/learn");
  };

  return (
    <Container maxWidth="md" sx={{ padding: 1 }}>
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
            <Stack direction="row">
              <Stack sx={{ flexGrow: 1 }}>
                <Typography textAlign="center" variant="h5">
                  {word.word}
                </Typography>
              </Stack>

              <Stack direction="row" alignItems="center" spacing={1}>
                <Typography
                  textAlign="center"
                  variant="subtitle1"
                  fontStyle="italic"
                  // sx={{ ml: 1 }}
                >
                  {word.category}
                </Typography>
              </Stack>
            </Stack>

            <Divider />

            {/* <Divider sx={{ my: 0.5 }} />

            <Stack>
              <Typography variant="subtitle2">
                {word.isLearned
                  ? `Word Learned`
                  : `Current guess streak: ${word.guessStreak}`}
              </Typography>
            </Stack>

            <Divider sx={{ my: 0.5 }} /> */}

            <List sx={visibleMeanings ? {} : { filter: "blur(20px)" }}>
              {word.meaningDtoList.length > 0 &&
                word.meaningDtoList.map((meaningDto, index) => (
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

                      {/* <Typography variant="body1">
                            {index + 1}. [{meaningDto.difficulty}]
                          </Typography> */}
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
                    onClick={() => handleWrongAnswer()}
                    startIcon={<CloseIcon />}
                  >
                    Wrong
                  </Button>
                  <Button
                    variant="contained"
                    onClick={() => handleCorrectAnswer()}
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
