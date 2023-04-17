import {
  Container,
  Divider,
  List,
  ListItem,
  Paper,
  Stack,
  Typography,
} from "@mui/material";
import WordListService from "../services/word-list";
import { useLoaderData } from "react-router-dom";
import WordItem from "../components/WordItem";

export async function loader({ params }) {
  const id = params.wordListId;
  const wordListData = (await WordListService.get(id)).data;
  console.log(wordListData);
  return wordListData;
}

export default function WordList() {
  const wordList = useLoaderData();

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Stack>
        <Stack alignItems="center">
          <Typography variant="h5">{wordList.name}</Typography>
        </Stack>
        <Divider />
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
