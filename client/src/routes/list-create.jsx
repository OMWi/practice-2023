import {
  Autocomplete,
  Box,
  Button,
  Container,
  Divider,
  FormControl,
  IconButton,
  InputLabel,
  List,
  MenuItem,
  Paper,
  Select,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import { useEffect, useState } from "react";
import { useOutletContext, useNavigate } from "react-router-dom";
import DifficultyService from "../services/difficulty";
import WordItem from "../components/WordItem";
import WordService from "../services/word";
import { Add, DeleteOutline } from "@mui/icons-material";

export default function WordListCreate() {
  const navigate = useNavigate();
  const [auth, setAuth] = useOutletContext();
  const [difficulties, setDifficulties] = useState([]);

  useEffect(() => {
    if (!auth) {
      navigate("/login");
    }
  }, [auth]);

  useEffect(() => {
    const fetchData = async () => {
      const difficulties = await DifficultyService.list();
      setDifficulties(difficulties.data);
      // todo: delete this
      const wordsPage = await WordService.list(0, 3);
      setWordsOfList(wordsPage.data.words);
      const wordsPage2 = await WordService.list();
      setWords(wordsPage2.data.words.map((word) => word.word));
    };
    fetchData();
  }, []);

  const [wordListName, setWordListName] = useState("");
  const [difficulty, setDifficulty] = useState("");
  const [words, setWords] = useState([]);

  const [wordsOfList, setWordsOfList] = useState([]);

  return (
    <Container maxWidth="lg" sx={{ padding: 1 }}>
      <Paper sx={{ padding: 2 }}>
        <Stack spacing={2}>
          <Typography variant="h6" textAlign="center">
            Create Wordlist
          </Typography>
          <Divider />
          <Stack direction="row" spacing={1}>
            <TextField
              size="small"
              label="Name"
              required
              fullWidth
              onChange={(event) => setWordListName(event.target.value)}
            />
            <FormControl size="small" sx={{ minWidth: "120px" }}>
              <InputLabel size="small" id="select-difficulty-label">
                Difficulty
              </InputLabel>
              <Select
                labelId="select-difficulty-label"
                value={difficulty}
                label="Difficulty"
                onChange={(event) => {
                  setDifficulty(event.target.value);
                }}
                // required
              >
                {difficulties.map((difficulty) => (
                  <MenuItem key={difficulty.id} value={difficulty.id}>
                    {difficulty.difficulty}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </Stack>
          <List>
            {wordsOfList.map((word) => (
              <Stack
                direction="row"
                spacing={1}
                alignItems="center"
                justifyContent="center"
              >
                <WordItem key={word.id} wordData={word} />
                <IconButton>
                  <DeleteOutline />
                </IconButton>
              </Stack>
            ))}
          </List>
          <Stack direction="row" spacing={1}>
            <Autocomplete
              fullWidth
              size="small"
              options={words}
              renderInput={(params) => (
                <TextField {...params} label="Add Word" />
              )}
            />
            <IconButton>
              <Add />
            </IconButton>
          </Stack>

          <Stack direction="row" spacing={1}>
            <Box sx={{ flexGrow: 1 }} />
            <Button variant="outlined" color="error">
              Cancel
            </Button>
            <Button variant="contained" disableElevation>
              Save
            </Button>
          </Stack>
        </Stack>
      </Paper>
    </Container>
  );
}
