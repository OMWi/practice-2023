import {
  Container,
  Divider,
  List,
  ListItem,
  Paper,
  Stack,
  Typography,
  Button,
  IconButton,
  Chip,
} from "@mui/material";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import { useLoaderData, useNavigate } from "react-router-dom";

import UserDataService from "../services/user-data";
import AuthService from "../services/auth";

export async function loader({ params }) {
  const wordId = params.wordId;
  const user = AuthService.getCurrentUser();
  if (!user) return null;
  const userWordData = (await UserDataService.getUserWord(user.userId, wordId))
    .data;
  return userWordData;
}

export default function UserWord() {
  const word = useLoaderData();
  const navigate = useNavigate();

  const handleDelete = async () => {
    const user = AuthService.getCurrentUser();
    if (!user) return;
    UserDataService.deleteUserWord(user.userId, word.wordId)
      .then((response) => {
        navigate(-1);
        return response;
      })
      .catch((error) => {
        return error;
      });
  };

  const getLearningStatus = () => {
    if (word.isLearned) {
      return `Awesome! You have mastered this word. Keep it up!`;
    }

    const lastIntervalChangeDate = new Date(word.intervalChangeDate);
    const nextRepeatDate = new Date();
    nextRepeatDate.setDate(lastIntervalChangeDate.getDate() + word.interval);
    const today = new Date();
    if (nextRepeatDate.valueOf() > today.valueOf()) {
      const differenceMs = nextRepeatDate.getTime() - today.getTime();
      const diffDays = Math.round(
        Math.abs(differenceMs / (1000 * 60 * 60 * 24)),
      );
      let message = "This word will be availabe for repetition";
      if (diffDays < 2) return `${message} tommorow`;
      return `${message} in ${diffDays} days`;
    } else {
      return "This word is availabe for repetition";
    }
  };

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Paper sx={{ padding: 2 }}>
        <Stack>
          <Stack direction="row" alignItems="center" spacing={1}>
            <Chip size="small" label={word.difficulty} color="primary" />
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
              <IconButton onClick={handleDelete}>
                <BookmarkIcon />
              </IconButton>
            </Stack>
          </Stack>

          {/* <Divider sx={{ my: 0.5 }} /> */}

          <Stack>
            <Typography variant="subtitle2">{getLearningStatus()}</Typography>
          </Stack>

          <Divider sx={{ my: 0.5 }} />

          <List>
            {word.meaningDtoList.length > 0 &&
              word.meaningDtoList.map((meaningDto, index) => (
                <ListItem disablePadding key={meaningDto.id} sx={{ my: 1 }}>
                  <Stack
                    direction="row"
                    alignItems="flex-start"
                    spacing={1}
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
        </Stack>
      </Paper>
    </Container>
  );
}
