import { Chip, ListItem, Paper, Stack, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function WordlistItem({ wordlistData }) {
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
      onClick={() => navigate(`/word-lists/${wordlistData.id}`)}
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
            <Stack>
              <Typography variant="h6">{wordlistData.name}</Typography>
              <Typography variant="subtitle1" sx={{ my: -0.5 }}>
                creator: "{wordlistData.owner.username}"
              </Typography>
            </Stack>
          </Stack>

          <Stack justifyContent="center">
            <Typography variant="body2" textAlign="end">
              Bookmarked: {wordlistData.popularity} times
            </Typography>
            <Typography variant="body2" textAlign="end">
              Favorited: {wordlistData.likes} times
            </Typography>
          </Stack>
        </Stack>
      </Paper>
    </ListItem>
  );
}
