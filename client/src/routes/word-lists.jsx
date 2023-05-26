import { Container, List, Stack, Typography } from "@mui/material";
import WordListService from "../services/word-list";
import WordlistItem from "../components/WordlistItem";
import { useLoaderData } from "react-router-dom";

export async function loader() {
  const wordListsData = (await WordListService.list()).data;
  return wordListsData;
}

export default function WordLists() {
  const wordLists = useLoaderData();

  return (
    <Container maxWidth="lg" sx={{ padding: 1 }}>
      {wordLists.length > 0 ? (
        <List>
          {wordLists.map((wordList) => (
            <WordlistItem key={wordList.id} wordlistData={wordList} />
          ))}
        </List>
      ) : (
        <Stack alignItems="center">
          <Typography variant="h4">No Word Lists found</Typography>
        </Stack>
      )}
    </Container>
  );
}
