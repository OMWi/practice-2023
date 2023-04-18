import { useEffect, useState } from "react";
import { useLoaderData, useOutletContext } from "react-router-dom";
import {
  Container,
  Divider,
  List,
  Stack,
  Typography,
  Button,
} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";

import WordListService from "../services/word-list";
import WordItem from "../components/WordItem";
import UserDataService from "../services/user-data";
import AuthService from "../services/auth";

export async function loader({ params }) {
  const id = params.wordListId;
  const wordListData = (await WordListService.get(id)).data;
  return wordListData;
}

export default function WordList() {
  const wordList = useLoaderData();

  const [inUserList, setInUserList] = useState(false);
  const auth = useOutletContext()[0];

  useEffect(() => {
    if (!auth) {
      setInUserList(false);
      return;
    }
    const user = AuthService.getCurrentUser();
    UserDataService.getUserWordList(user.userId, wordList.id)
      .then((response) => {
        setInUserList(true);
        return response;
      })
      .catch((error) => console.log(error));
  }, [inUserList]);

  const handleAdd = async () => {
    const user = AuthService.getCurrentUser();
    if (!user) return;
    UserDataService.addUserWordList(user.userId, wordList.id)
      .then((response) => {
        setInUserList(true);
        return response;
      })
      .catch((error) => console.log(error));
  };

  const handleDelete = async () => {
    const user = AuthService.getCurrentUser();
    if (!user) return;
    UserDataService.deleteUserWordList(user.userId, wordList.id)
      .then((response) => {
        setInUserList(false);
        return response;
      })
      .catch((error) => console.log(error));
  };

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Stack>
        <Stack direction="row" sx={{ width: 1 }}>
          <Stack sx={{ flexGrow: 1 }} alignItems="center">
            <Typography variant="h5">{wordList.name}</Typography>
          </Stack>
          {auth &&
            (inUserList ? (
              <Button
                variant="outlined"
                endIcon={<DeleteOutlineIcon />}
                onClick={() => handleDelete()}
                color="error"
              >
                Delete
              </Button>
            ) : (
              <Button
                variant="outlined"
                endIcon={<AddIcon />}
                onClick={() => handleAdd()}
              >
                Add
              </Button>
            ))}
        </Stack>
        <Divider sx={{ my: 0.5 }} />
        {wordList.wordDtoList.length > 0 && (
          <List>
            {wordList.wordDtoList.map((word) => (
              <WordItem
                key={word.id}
                wordData={{
                  id: word.id,
                  text: word.text,
                  category: word.categoryDto.category,
                }}
              />
            ))}
          </List>
        )}
      </Stack>
    </Container>
  );
}
