import { Chip, ListItem, Paper, Stack, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";

export default function UserWordlistItem({ wordlistData }) {
  const navigate = useNavigate();

  return (
    <ListItem
      disablePadding
      sx={{
        mb: 1,
        "&:hover": {
          cursor: "pointer",
        },
      }}
      onClick={() => navigate(`/word-lists/${wordlistData.wordListId}`)}
    >
      <Paper
        square
        sx={{
          width: 1,
          "&:hover": {
            bgcolor: "selected.main",
          },
        }}
      >
        <Stack sx={{ padding: 1 }} direction="row">
          <Stack
            direction="row"
            alignItems="center"
            spacing={1}
            sx={{ flexGrow: 1 }}
          >
            <Chip
              size="small"
              label={wordlistData.difficulty}
              variant="outlined"
            />
            <Typography variant="h6">{wordlistData.name}</Typography>
          </Stack>

          <Stack justifyContent="center" alignItems="center">
            {wordlistData.isFavorite ? (
              <FavoriteIcon />
            ) : (
              <FavoriteBorderIcon />
            )}
          </Stack>
        </Stack>
      </Paper>
    </ListItem>
  );
}
