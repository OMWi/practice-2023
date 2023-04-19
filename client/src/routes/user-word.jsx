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
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import { useLoaderData, useNavigate } from "react-router-dom";

import UserDataService from "../services/user-data";
import AuthService from "../services/auth";

export async function loader({ params }) {
  const wordId = params.wordId;
  const user = AuthService.getCurrentUser();
  if (!user) return null;
  const userWordData = (await UserDataService.getUserWord(user.userId, wordId))
    .data;
  console.log(userWordData);
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
        navigate("/profile");
        return response;
      })
      .catch((error) => {
        return error;
      });
  };

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Paper sx={{ padding: 2 }}>
        <Stack>
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
              <Button
                variant="outlined"
                endIcon={<DeleteOutlineIcon />}
                onClick={() => handleDelete()}
                color="error"
              >
                Delete
              </Button>
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

          <List>
            {word.meaningDtoList.length > 0 &&
              word.meaningDtoList.map((meaningDto, index) => (
                <ListItem disablePadding key={meaningDto.id}>
                  <Stack
                    direction="row"
                    alignItems="flex-start"
                    sx={{ width: 1 }}
                  >
                    <Typography variant="body1" sx={{ minWidth: "50px" }}>
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
        </Stack>
      </Paper>
    </Container>
  );
}
