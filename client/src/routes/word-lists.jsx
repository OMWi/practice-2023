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
    <Container maxWidth="md" sx={{ padding: 0 }}>
      {wordLists.length > 0 ? (
        <List>
          {wordLists.map((wordList) => (
            <WordlistItem
              key={wordList.id}
              wordlistData={{
                id: wordList.id,
                name: wordList.name,
                popularity: wordList.popularity,
              }}
            />
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
