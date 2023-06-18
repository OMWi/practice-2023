import { Chip, ListItem, Paper, Stack, Typography } from "@mui/material";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import HourglassEmptyIcon from "@mui/icons-material/HourglassEmpty";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";

export default function UserWordItem({ userWordData }) {
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
      onClick={() => {
        navigate(`/profile/words/${userWordData.wordId}`);
      }}
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
        <Stack sx={{ padding: 1, width: 1 }} spacing={0.5}>
          <Stack direction="row" alignItems="center" spacing={1}>
            <Chip
              size="small"
              label={userWordData.difficulty}
              variant="outlined"
            />
            <Typography variant="h6" sx={{ display: "flex", flexGrow: 1 }}>
              {userWordData.word}
            </Typography>
            <Typography
              variant="subtitle1"
              display="inline"
              fontStyle="italic"
              sx={{ ml: 1 }}
            >
              {userWordData.category}
            </Typography>
          </Stack>

          <Stack direction="row" spacing={1}>
            {userWordData.isLearned ? (
              <>
                <CheckCircleIcon fontSize="small" />
                <Typography variant="subtitle2">Mastered</Typography>
              </>
            ) : (
              <>
                <HourglassEmptyIcon fontSize="small" />
                <Typography variant="subtitle2">Needs work</Typography>
              </>
            )}
          </Stack>
        </Stack>
      </Paper>
    </ListItem>
  );
}

UserWordItem.propTypes = {
  userWordData: PropTypes.shape({
    wordId: PropTypes.number,
    word: PropTypes.string,
    isLearned: PropTypes.bool,
    guessStreak: PropTypes.number,
    category: PropTypes.string,
  }),
};
