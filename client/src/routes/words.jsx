import { Container, List, ListItem, Stack, Typography } from "@mui/material";
import { useLoaderData } from "react-router-dom";

export async function loader() {
  const wordsData = [
    {
      id: 1,
      text: "word 1",
    },
    {
      id: 2,
      text: "word 2",
    },
    {
      id: 3,
      text: "word 3",
    },
  ];
  return wordsData;
}

export default function Words() {
  const words = useLoaderData();

  return (
    <Container maxWidth="md" sx={{ padding: 0 }}>
      <Stack alignItems="center">
        <List>
          {words.length &&
            words.map((word) => (
              <ListItem key={word.id}>
                <Typography variant="h6">{word.text}</Typography>
              </ListItem>
            ))}
        </List>
      </Stack>
    </Container>
  );
}
