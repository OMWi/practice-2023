import { useState, useEffect } from "react";
import { useLoaderData, useLocation, useNavigate } from "react-router-dom";
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
import SearchIcon from "@mui/icons-material/Search";
import WordService from "../services/word";
import WordCategoryService from "../services/word-category";
import DifficultyService from "../services/difficulty";
import WordItem from "../components/WordItem";

export async function loader({ request }) {
  const searchParams = new URL(request.url).searchParams;
  const wordsPage = (
    await WordService.list(
      searchParams.get("page") || 1,
      searchParams.get("size") || 10,
      searchParams.get("search"),
      searchParams.get("category"),
      searchParams.get("difficulty"),
    )
  ).data;
  return wordsPage;
}

export default function Words() {
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("md"));
  const navigate = useNavigate();

  const loaderData = useLoaderData();
  const [wordsPage, setWordsPage] = useState(loaderData);
  useEffect(() => {
    setWordsPage(loaderData);
  }, [loaderData]);

  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const page = parseInt(query.get("page") || "1", 10);
  const size = parseInt(query.get("size") || "10", 10);

  const [categories, setCategories] = useState([]);
  const [difficulties, setDifficulties] = useState([]);
  const [searchText, setSearchText] = useState(query.get("search") || "");

  const [difficulty, setDifficulty] = useState(query.get("difficulty") || "");
  const [category, setCategory] = useState(query.get("category") || "");

  useEffect(() => {
    const fetchData = async () => {
      const categories = await WordCategoryService.list();
      setCategories(categories.data);
      const difficulties = await DifficultyService.list();
      setDifficulties(difficulties.data);
    };
    fetchData();
  }, []);

  const getNavigationUrl = (page, size) => {
    let url = "/words";
    if (
      page === 1 &&
      size === 10 &&
      searchText === "" &&
      difficulty === "" &&
      category === ""
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
    if (searchText !== "") {
      url += `search=${searchText}&`;
    }
    if (category !== "") {
      url += `category=${category}&`;
    }
    if (difficulty !== "") {
      url += `difficulty=${difficulty}&`;
    }
    return url.substring(0, url.length - 1);
  };

  const handleSearch = async () => {
    navigate(getNavigationUrl(page, size));
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
            <InputLabel id="select-category-label" size="small">
              Category
            </InputLabel>
            <Select
              labelId="select-category-label"
              value={category}
              label="Category"
              onChange={(event) => {
                setCategory(event.target.value);
              }}
            >
              <MenuItem value="">Any</MenuItem>
              {categories.map((category) => (
                <MenuItem key={category.id} value={category.id}>
                  {category.category}
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <FormControl size="small" fullWidth sx={{ minWidth: "120px" }}>
            <InputLabel id="select-difficulty-label" size="small">
              Difficulty
            </InputLabel>
            <Select
              labelId="select-difficulty-label"
              value={difficulty}
              label="Difficulty"
              onChange={(event) => {
                setDifficulty(event.target.value);
              }}
            >
              <MenuItem value="">Any</MenuItem>
              {difficulties.map((difficulty) => (
                <MenuItem key={difficulty.id} value={difficulty.id}>
                  {difficulty.difficulty}
                </MenuItem>
              ))}
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
        {wordsPage.words.map((word) => (
          <WordItem key={word.id} wordData={word} />
        ))}
      </List>
      {wordsPage.words.length < wordsPage.totalItems && (
        <Stack alignItems="center">
          <Pagination
            page={page}
            count={wordsPage.totalPages}
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
