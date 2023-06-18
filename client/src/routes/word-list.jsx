import { useEffect, useState } from "react";
import { useLoaderData, useOutletContext } from "react-router-dom";
import {
  Container,
  Divider,
  List,
  Stack,
  Typography,
  Button,
  Chip,
  IconButton,
} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import BookmarkIcon from "@mui/icons-material/Bookmark";

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

  const [isAdded, setIsAdded] = useState(false);
  const [isFavorite, setIsFavorite] = useState(false);
  const [isGuest, setIsGuest] = useState(true);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (!user) {
      return;
    }
    setIsGuest(false);

    UserDataService.getUserWordList(user.userId, wordList.id)
      .then((response) => {
        setIsAdded(true);
        setIsFavorite(response.data.isFavorite);
        return response;
      })
      .catch(() => {});
  }, []);

  const handleAdd = async () => {
    const user = AuthService.getCurrentUser();
    if (!user) return;

    UserDataService.addUserWordList(user.userId, wordList.id, false).then(
      (response) => {
        setIsAdded(true);
      },
    );
  };

  const handleRemove = async () => {
    const user = AuthService.getCurrentUser();
    if (!user) return;

    UserDataService.deleteUserWordList(user.userId, wordList.id).then(
      (response) => {
        setIsAdded(false);
        setIsFavorite(false);
      },
    );
  };

  const handleFavorited = async () => {
    const user = AuthService.getCurrentUser();
    if (!user) return;

    if (isAdded) {
      UserDataService.updateUserWordList(user.userId, wordList.id, true).then(
        (response) => {
          setIsFavorite(true);
        },
      );
      return;
    }

    UserDataService.addUserWordList(user.userId, wordList.id, true).then(
      (response) => {
        setIsAdded(true);
        setIsFavorite(true);
      },
    );
  };

  const handleUnfavorited = async () => {
    const user = AuthService.getCurrentUser();
    if (!user) return;

    UserDataService.updateUserWordList(user.userId, wordList.id, false).then(
      (response) => {
        setIsFavorite(false);
      },
    );
  };

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Stack>
        <Stack direction="row" sx={{ width: 1 }}>
          <Stack
            direction="row"
            justifyContent="center"
            spacing={1}
            sx={{ flexGrow: 1 }}
            alignItems="center"
          >
            <Chip size="small" label={wordList.difficulty} color="primary" />
            <Stack>
              <Typography variant="h5">{wordList.name}</Typography>
              <Typography variant="body1" sx={{ my: -0.5 }}>
                creator: "{wordList.owner.username}"
              </Typography>
            </Stack>
          </Stack>

          {isGuest ? (
            <></>
          ) : isAdded ? (
            <IconButton onClick={handleRemove}>
              <BookmarkIcon />
            </IconButton>
          ) : (
            <IconButton onClick={handleAdd}>
              <BookmarkBorderIcon />
            </IconButton>
          )}

          {isGuest ? (
            <></>
          ) : isFavorite ? (
            <IconButton onClick={handleUnfavorited}>
              <FavoriteIcon />
            </IconButton>
          ) : (
            <IconButton onClick={handleFavorited}>
              <FavoriteBorderIcon />
            </IconButton>
          )}
          {/* {auth &&
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
            ))} */}
        </Stack>
        <Divider sx={{ my: 0.5 }} />
        {wordList.wordDtoList.length > 0 && (
          <List>
            {wordList.wordDtoList.map((word) => (
              <WordItem key={word.id} wordData={word} />
            ))}
          </List>
        )}
      </Stack>
    </Container>
  );
}
