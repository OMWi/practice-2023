import { useState, useEffect } from "react";
import { useLoaderData, useLocation } from "react-router-dom";
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
    await WordService.list(searchParams.get("page"), searchParams.get("size"))
  ).data;
  return wordsPage;
}

export default function Words() {
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("md"));

  const [wordsPage, setWordsPage] = useState(useLoaderData());

  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const page = parseInt(query.get("page") || "1", 10);

  const [categories, setCategories] = useState([]);
  const [difficulties, setDifficulties] = useState([]);
  const [searchText, setSearchText] = useState("");

  const [difficulty, setDifficulty] = useState("");
  const [category, setCategory] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      const categories = await WordCategoryService.list();
      setCategories(categories.data);
      const difficulties = await DifficultyService.list();
      setDifficulties(difficulties.data);
    };
    fetchData();
  }, []);

  const handleSearch = async () => {
    const newWordsPage = (
      await WordService.list(page, size, searchText, category, difficulty)
    ).data;
    setWordsPage(newWordsPage);
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
            onChange={(event) => setSearchText(event.target.value)}
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
                to={`/words${item.page === 1 ? "" : `?page=${item.page}`}`}
                {...item}
              />
            )}
          />
        </Stack>
      )}
    </Container>
  );
}
