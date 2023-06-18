import {
  Autocomplete,
  Box,
  Button,
  Container,
  Divider,
  FormControl,
  FormHelperText,
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
import { ValidationError } from "../utils";

export default function WordListCreate() {
  const navigate = useNavigate();
  const auth = useOutletContext()[0];

  const [globalError, setGlobalError] = useState(ValidationError(false, ""));

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
      setGlobalError(ValidationError(true, "List name required"));
      return;
    }
    if (difficulty === "") {
      setGlobalError(ValidationError(true, "Difficulty required"));
      return;
    }
    if (wordsOfList.length === 0) {
      setGlobalError(ValidationError(true, "List can't be empty"));
      return;
    }
    WordListService.create(
      wordListName,
      difficulty,
      wordsOfList.map((word) => word.id),
    )
      .then((res) => {
        navigate(`/word-lists/${res.data.id}`);
      })
      .catch((err) => {
        console.log(err);
        if (err.response.status === 403) {
          navigate(-1);
        }
      });
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
          {wordsOfList.length > 0 && (
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
          )}

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

          <FormHelperText error={globalError.error}>
            {globalError.error && globalError.helperText}
          </FormHelperText>

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
