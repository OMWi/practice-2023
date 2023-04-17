import {
  Container,
  Divider,
  List,
  ListItem,
  Paper,
  Stack,
  Typography,
} from "@mui/material";
import WordService from "../services/word";
import { useLoaderData } from "react-router-dom";

export async function loader({ params }) {
  const id = params.wordId;
  const wordData = (await WordService.get(id)).data;
  return wordData;
}

export default function Word() {
  const word = useLoaderData();

  return (
    <Container maxWidth="md" sx={{ padding: 2 }}>
      <Paper sx={{ padding: 2 }}>
        <Stack>
          <Stack direction="row" alignItems="flex-end">
            <Typography variant="h6" sx={{ display: "flex", flexGrow: 1 }}>
              {word.text}
            </Typography>
            <Typography variant="subtitle1" fontStyle="italic" sx={{ ml: 1 }}>
              {word.categoryDto.category}
            </Typography>
          </Stack>

          <Divider />

          <List>
            {word.meaningDtoList.length > 0 &&
              word.meaningDtoList.map((meaningDto) => (
                <ListItem disablePadding key={meaningDto.id}>
                  <Typography variant="body2">
                    Level {meaningDto.level}
                  </Typography>
                  <Typography variant="body1">{meaningDto.text}</Typography>
                </ListItem>
              ))}
          </List>
        </Stack>
      </Paper>
    </Container>
  );
}
