import { Container, List, Typography } from "@mui/material";

export async function loader() {
  const word = {
    id: 1,
    text: "word",
    meanings: [
      {
        id: 1,
        level: 1,
        text: "meaning 1",
      },
      {
        id: 2,
        level: 2,
        text: "meaning 2",
      },
    ],
    category: "noun",
  };
  return word;
}

export default function Word() {
  const word = useLoaderData();

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Paper sx={{ padding: 1 }}>
        <Stack>
          <Typography variant="h3">{word.text}</Typography>
          <Typography variant="subtitle1">{word.category}</Typography>
          <List>
            {meanings.length &&
              meanings.map((meaning) => (
                <ListItem key={meaning.id}>
                  <Typography variant="body2">Level {meaning.level}</Typography>
                  <Typography variant="body1">{meaning.text}</Typography>
                </ListItem>
              ))}
          </List>
        </Stack>
      </Paper>
    </Container>
  );
}
