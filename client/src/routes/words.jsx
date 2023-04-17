import { Container, List, Stack, Typography } from "@mui/material";
import { useLoaderData } from "react-router-dom";

import WordService from "../services/word";
import WordItem from "../components/WordItem";

export async function loader() {
  const wordsData = (await WordService.list()).data;
  return wordsData;
}

export default function Words() {
  const words = useLoaderData();

  return (
    <Container maxWidth="md" sx={{ padding: 0 }}>
      {words.length > 0 ? (
        <List>
          {words.map((word) => (
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
      ) : (
        <Stack alignItems="center">
          <Typography variant="h4">No Words found</Typography>
        </Stack>
      )}
    </Container>
  );
}
