import {
  Box,
  Button,
  Container,
  Divider,
  FormControl,
  InputAdornment,
  InputLabel,
  List,
  MenuItem,
  Select,
  Stack,
  TextField,
  Typography,
  useMediaQuery,
  useTheme,
} from "@mui/material";
import WordListService from "../services/word-list";
import WordlistItem from "../components/WordlistItem";
import { useLoaderData, useLocation } from "react-router-dom";
import SearchIcon from "@mui/icons-material/Search";
import SortIcon from "@mui/icons-material/Sort";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import { useEffect, useState } from "react";
import DifficultyService from "../services/difficulty";

export async function loader() {
  const wordListsData = (await WordListService.list()).data;
  return wordListsData;
}

export default function WordLists() {
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("md"));

  const wordLists = useLoaderData();

  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const page = parseInt(query.get("page") || "1", 10);

  const [difficulties, setDifficulties] = useState([]);

  const [difficulty, setDifficulty] = useState("");

  const [sort, setSort] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      const difficulties = await DifficultyService.list();
      setDifficulties(difficulties.data);
    };
    fetchData();
  }, []);

  return (
    <Container maxWidth="lg" sx={{ padding: 1 }}>
      <Stack
        direction={isSmallScreen ? "column" : "row"}
        spacing={1}
        sx={{ mb: 1 }}
      >
        <Box sx={{ flexGrow: 1 }}>
          <TextField
            size="small"
            fullWidth
            label="Search"
            InputProps={{
              endAdornment: (
                <InputAdornment position="end">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
          ></TextField>
        </Box>

        <Stack direction="row" spacing={1}>
          <FormControl size="small" fullWidth sx={{ minWidth: "120px" }}>
            <InputLabel id="select-difficulty-label" size="small">
              Difficulty
            </InputLabel>
            <Select
              labelId="select-difficulty-label"
              value={difficulty}
              label="Difficulty"
              onChange={(event) => setDifficulty(event.target.value)}
            >
              <MenuItem value="">Any</MenuItem>
              {difficulties.map((difficulty) => (
                <MenuItem key={difficulty.id} value={difficulty.difficulty}>
                  {difficulty.difficulty}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <FormControl size="small" sx={{ minWidth: "120px" }}>
            <InputLabel id="select-sort-label" size="small">
              Sort By
            </InputLabel>
            <Select
              labelId="select-sort-label"
              value={sort}
              onChange={(e) => setSort(e.target.value)}
              inputProps={{}}
              label="Sort"
            >
              <MenuItem value="">Nothing</MenuItem>
              <MenuItem value="popularity">Popularity</MenuItem>
              <MenuItem value="rating">Rating</MenuItem>
            </Select>
          </FormControl>
          <Button
            variant="contained"
            disableElevation
            size="small"
            sx={{ minWidth: "80px" }}
          >
            Search
          </Button>
        </Stack>
      </Stack>
      <Divider />
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
