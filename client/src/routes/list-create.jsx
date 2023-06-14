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
import WordListService from "../services/word-list";

export default function WordListCreate() {
  const navigate = useNavigate();
  const auth = useOutletContext()[0];

  useEffect(() => {
    if (!auth) {
      navigate("/login");
    }
  }, [auth]);

  useEffect(() => {
    const fetchData = async () => {
      const difficulties = await DifficultyService.list();
      setDifficulties(difficulties.data);
    };
    fetchData();
  }, []);

  const [difficulties, setDifficulties] = useState([]);
  const [wordsOptions, setWordsOptions] = useState([]);
  const [currentOption, setCurrentOption] = useState("");

  const [wordListName, setWordListName] = useState("");
  const [difficulty, setDifficulty] = useState("");
  const [wordsOfList, setWordsOfList] = useState([]);

  const handleWordListSave = async () => {
    if (wordListName === "") {
      // add validation message
      console.log("wordListName validation");
      return;
    }
    if (difficulty === "") {
      // add validation message
      console.log("difficulty validation");
      return;
    }
    if (wordsOfList.length === 0) {
      // add validation message
      console.log("wordsOfList validation");
      return;
    }
    const res = await WordListService.create(
      wordListName,
      difficulty,
      wordsOfList.map((word) => word.id),
    );
    navigate(`/word-lists/${res.data.id}`);
  };

  const handleWordAdd = (word) => {
    if (word === "") {
      // add validation message
      return;
    }
    if (wordsOfList.find((item) => item.id === word.id)) {
      // add validation message
      return;
    }

    setWordsOfList([...wordsOfList, word]);
  };

  const handleWordDelete = (word) => {
    const newWordsOfList = wordsOfList.filter((item) => item.id !== word.id);
    setWordsOfList(newWordsOfList);
  };

  const handleAutocompleteChange = async (searchText) => {
    if (searchText === "") {
      setWordsOfList([]);
      return;
    }
    const res = await WordService.list(1, 5, searchText);
    setWordsOptions(res.data.words);
  };

  return (
    <Container maxWidth="lg" sx={{ padding: 1 }}>
      <Paper sx={{ padding: 2 }}>
        <Stack spacing={2}>
          <Typography variant="h5" textAlign="center">
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
              <InputLabel size="small" id="select-difficulty-label" required>
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
                key={word.id}
              >
                <WordItem wordData={word} />
                <IconButton onClick={() => handleWordDelete(word)}>
                  <DeleteOutline />
                </IconButton>
              </Stack>
            ))}
          </List>
          <Stack direction="row" spacing={1}>
            <Autocomplete
              fullWidth
              size="small"
              options={wordsOptions}
              getOptionLabel={(option) => option.word}
              noOptionsText="Type words to search"
              // value={currentOption}
              onChange={(event, newValue) => {
                setCurrentOption(newValue);
              }}
              renderInput={(params) => (
                <TextField
                  {...params}
                  label="Add Word"
                  onChange={(event) =>
                    handleAutocompleteChange(event.target.value)
                  }
                />
              )}
            />
            <IconButton onClick={() => handleWordAdd(currentOption)}>
              <Add />
            </IconButton>
          </Stack>

          <Stack direction="row" spacing={1}>
            <Box sx={{ flexGrow: 1 }} />
            <Button
              variant="outlined"
              color="error"
              onClick={() => navigate(-1)}
            >
              Cancel
            </Button>
            <Button
              variant="contained"
              disableElevation
              onClick={() => handleWordListSave()}
            >
              Save
            </Button>
          </Stack>
        </Stack>
      </Paper>
    </Container>
  );
}
