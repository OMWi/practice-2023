import {
  Box,
  Button,
  Container,
  Divider,
  FormControl,
  InputAdornment,
  InputLabel,
  Link,
  List,
  MenuItem,
  Pagination,
  PaginationItem,
  Select,
  Stack,
  TextField,
  Typography,
  useMediaQuery,
  useTheme,
} from "@mui/material";
import WordListService from "../services/word-list";
import WordlistItem from "../components/WordlistItem";
import { useLoaderData, useLocation, useNavigate } from "react-router-dom";
import SearchIcon from "@mui/icons-material/Search";
import SortIcon from "@mui/icons-material/Sort";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import { useEffect, useState } from "react";
import DifficultyService from "../services/difficulty";

export async function loader({ request }) {
  const searchParams = new URL(request.url).searchParams;
  const wordListsData = (
    await WordListService.list(
      searchParams.get("page") || 1,
      searchParams.get("size") || 10,
      searchParams.get("search"),
      searchParams.get("difficulty"),
      searchParams.get("sortBy"),
    )
  ).data;
  return wordListsData;
}

export default function WordLists() {
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("md"));
  const navigate = useNavigate();

  const loaderData = useLoaderData();
  const [wordListsPage, setWordListsPage] = useState(loaderData);
  useEffect(() => {
    setWordListsPage(loaderData);
  }, [loaderData]);

  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const page = parseInt(query.get("page") || "1", 10);
  const size = parseInt(query.get("size") || "10", 10);

  const [difficulties, setDifficulties] = useState([]);

  const [difficulty, setDifficulty] = useState("");
  const [sortBy, setSortBy] = useState("");
  const [searchText, setSearchText] = useState(query.get("search") || "");

  useEffect(() => {
    const fetchData = async () => {
      const difficulties = await DifficultyService.list();
      setDifficulties(difficulties.data);
    };
    fetchData();
  }, []);

  const getNavigationUrl = (page, size) => {
    let url = "/word-lists";
    if (
      page === 1 &&
      size === 10 &&
      difficulty === "" &&
      sortBy === "" &&
      searchText === ""
    ) {
      return url;
    }

    url += "?";
    if (page !== 1) {
      url += `page=${page}&`;
    }
    if (size !== 10) {
      url += `size=${size}&`;
    }
    if (difficulty !== "") {
      url += `difficulty=${difficulty}&`;
    }
    if (sortBy !== "") {
      url += `sortBy=${sortBy}&`;
    }
    if (searchText !== "") {
      url += `search=${searchText}&`;
    }
    console.log("nav url: ", url);
    return url.substring(0, url.length - 1);
  };

  const handleSearch = async () => {
    navigate(getNavigationUrl(page, size));
    // let url = "/word-lists";
    // if (searchText === "" && difficulty === "" && sortBy === "") {
    //   navigate("");
    // }
    // url += "?";
    // if (searchText !== "") {
    //   url += `search=${searchText}&`;
    // }
    // if (difficulty !== "") {
    //   url += `difficulty=${difficulty}&`;
    // }
    // if (sortBy !== "") {
    //   url += `sortBy=${sortBy}&`;
    // }
    // url = url.substring(0, url.length - 1);
    // navigate(url);
  };

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
            value={searchText}
            onChange={(event) => {
              setSearchText(event.target.value);
            }}
            onKeyUp={(e) => {
              if (e.key === "Enter") handleSearch();
            }}
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
                <MenuItem key={difficulty.id} value={difficulty.id}>
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
              value={sortBy}
              onChange={(e) => setSortBy(e.target.value)}
              inputProps={{}}
              label="Sort"
            >
              <MenuItem value="">Nothing</MenuItem>
              <MenuItem value="popularity">Popularity</MenuItem>
              <MenuItem value="likes">Rating</MenuItem>
            </Select>
          </FormControl>
          <Button
            variant="contained"
            disableElevation
            size="small"
            sx={{ minWidth: "80px" }}
            onClick={() => {
              handleSearch();
            }}
          >
            Search
          </Button>
        </Stack>
      </Stack>
      <Divider />
      <List>
        {wordListsPage.wordLists.map((wordList) => (
          <WordlistItem key={wordList.id} wordlistData={wordList} />
        ))}
      </List>
      {wordListsPage.wordLists.length < wordListsPage.totalItems && (
        <Stack alignItems="center">
          <Pagination
            page={page}
            count={wordListsPage.totalPages}
            variant="outlined"
            shape="rounded"
            renderItem={(item) => (
              <PaginationItem
                component={Link}
                to={getNavigationUrl(item.page, size)}
                {...item}
              />
            )}
          />
        </Stack>
      )}
    </Container>
  );
}
