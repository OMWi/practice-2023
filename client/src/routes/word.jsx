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
import AddIcon from "@mui/icons-material/Add";
import { useLoaderData, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

import WordService from "../services/word";
import AuthService from "../services/auth";
import UserDataService from "../services/user-data";

export async function loader({ params }) {
  const id = params.wordId;
  const wordData = (await WordService.get(id)).data;
  return wordData;
}

export default function Word() {
  const word = useLoaderData();
  const navigate = useNavigate();

  const [canAdd, setCanAdd] = useState(false);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (!user) {
      setCanAdd(false);
      return;
    }
    UserDataService.getUserWord(user.userId, word.id)
      .then((response) => {
        if (response.status === 200) {
          setCanAdd(false);
        }
        return response;
      })
      .catch((error) => {
        if (error.response.status === 404) {
          setCanAdd(true);
        }
      });
  }, [canAdd]);

  const handleAdd = async () => {
    const user = AuthService.getCurrentUser();
    if (!user || !canAdd) return;
    const result = await UserDataService.addUserWord(user.userId, word.id);
    console.log(result);
    if (result.status === 200) {
      navigate("/profile");
    }
  };

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Paper sx={{ padding: 2 }}>
        <Stack>
          <Stack direction="row" alignItems="flex-end">
            <Typography variant="h5" sx={{ display: "flex", flexGrow: 1 }}>
              {word.text}
            </Typography>
            <Stack direction="row" alignItems="center" spacing={1}>
              <Typography
                textAlign="center"
                variant="subtitle1"
                fontStyle="italic"
                sx={{ ml: 1 }}
              >
                {word.categoryDto.category}
              </Typography>
              {canAdd && (
                <Button
                  variant="outlined"
                  endIcon={<AddIcon />}
                  onClick={() => handleAdd()}
                >
                  Add
                </Button>
              )}
            </Stack>
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
