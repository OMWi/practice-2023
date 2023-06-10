import { Chip, ListItem, Paper, Stack, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function WordItem({ wordData }) {
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
      onClick={() => navigate(`/words/${wordData.id}`)}
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
        <Stack sx={{ padding: 1, width: 1 }}>
          <Stack direction="row" alignItems="center" spacing={1}>
            <Chip size="small" label={wordData.difficulty} variant="outlined" />

            <Typography variant="h6" sx={{ display: "flex", flexGrow: 1 }}>
              {wordData.word}
            </Typography>
            <Typography
              variant="subtitle1"
              display="inline"
              fontStyle="italic"
              sx={{ ml: 1 }}
            >
              {wordData.categoryDto.category}
            </Typography>
          </Stack>
        </Stack>
      </Paper>
    </ListItem>
  );
}
